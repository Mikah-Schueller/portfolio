import torch
import random
import numpy as np
from collections import deque
from game import FlappyBird, Bird, Pipe
from model import Linear_QNet, QTrainer
from helper import plot

MAX_MEMORY = 100_000
BATCH_SIZE = 1000
LR = 0.001

class Agent:

   def __init__(self):
      self.num_games = 0
      self.epsilon = 0 # randomness
      self.gamma = 0.9 # discount rate
      self.memory = deque(maxlen = MAX_MEMORY) # popleft() if memory exceeds MAX_MEMORY
      self.model = Linear_QNet(3, 256, 2)
      self.trainer = QTrainer(self.model, lr = LR, gamma = self.gamma)
   
   def get_state(self, game):
      height = game.bird.height

      if game.pipes[0].position >= 17:
         current_pipe = game.pipes[0]
      else:
         current_pipe = game.pipes[1]

      opening_closest_pipe = current_pipe.opening

      state = [
         # Bird above opening
         height < opening_closest_pipe - 32,
         # Bird below opening
         height > opening_closest_pipe + 32,
         # Bird close to bottom pipe
         (height < opening_closest_pipe + 32 and height >= opening_closest_pipe + 27)
      ]

      return np.array(state, dtype = int)
   
   def remember(self, state, action, reward, next_state, game_over):
      self.memory.append((state, action, reward, next_state, game_over)) # popleft() if MAX_MEMORY is reached

   def train_long_memory(self):
      if len(self.memory) > BATCH_SIZE:
         mini_sample = random.sample(self.memory, BATCH_SIZE) # list of tuples
      else:
         mini_sample = self.memory

      states, actions, rewards, next_states, game_overs = zip(*mini_sample)
      self.trainer.train_step(states, actions, rewards, next_states, game_overs)

   def train_short_memory(self, state, action, reward, next_state, game_over):
      self.trainer.train_step(state, action, reward, next_state, game_over)

   def get_action(self, state):
      # random moves: tradeoff between exploration and exploitation
      self.epsilon = 15 - self.num_games
      final_move = [0,0]
      if random.randint(0, 200) < self.epsilon:
         skewed_random = 1 if random.random() <= 0.05 else 0
         final_move[skewed_random] = 1
      else:
         state0 = torch.tensor(state, dtype = torch.float)
         prediction = self.model(state0)
         move = torch.argmax(prediction).item()
         # print(prediction)
         final_move[move] = 1
         # print(final_move)
         # print(prediction, final_move)
      return final_move

def train():
   plot_scores = []
   plot_mean_scores = []
   total_score = 0
   best_score = 0
   agent = Agent()
   game = FlappyBird()
   
   while True:
      # get the old state
      state_old = agent.get_state(game)
      # get move
      final_move = agent.get_action(state_old)
      # perform move and get new state
      reward, game_over, score = game.play_step(final_move)
      state_new = agent.get_state(game)
      # train short memory
      agent.train_short_memory(state_old, final_move, reward, state_new, game_over)
      #remember
      agent.remember(state_old, final_move, reward, state_new, game_over)

      if game_over:
         # train long memory, plot result
         game.reset()
         agent.num_games += 1
         agent.train_long_memory()

         if score > best_score:
            best_score = score
            agent.model.save()

         print('Game', agent.num_games, 'Score', score, 'Record', best_score)

         plot_scores.append(score)
         total_score += score
         mean_score = total_score / agent.num_games
         plot_mean_scores.append(mean_score)
         plot(plot_scores, plot_mean_scores)


if __name__ == '__main__':
   train()