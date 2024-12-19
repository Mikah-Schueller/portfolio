import torch
import torch.nn as nn
import torch.optim as optim
import torch.nn.functional as F
import os

class Linear_QNet(nn.Module):
   def __init__(self, input_size, hidden_size, output_size):
      super().__init__()
      self.linear1 = nn.Linear(input_size, hidden_size)
      self.linear2 = nn.Linear(hidden_size, output_size)
   
   def forward(self, x):
      x = F.relu(self.linear1(x))
      x = self.linear2(x)
      return x
   
   def save(self, file_name = 'model.pth'):
      model_foler_path = './model'
      if not os.path.exists(model_foler_path):
         os.makedirs(model_foler_path)
      
      file_name = os.path.join(model_foler_path, file_name)
      torch.save(self.state_dict(), file_name)

class QTrainer:
   def __init__(self, model, lr, gamma):
      self.model = model
      self.lr = lr
      self.gamma = gamma
      self.optimizer = optim.Adam(model.parameters(), lr = self.lr)
      self.criterion = nn.MSELoss()

   def train_step(self, state, action, reward, next_state, game_over):
      state = torch.tensor(state, dtype = torch.float)
      action = torch.tensor(action, dtype = torch.float)
      reward = torch.tensor(reward, dtype = torch.float)
      next_state = torch.tensor(next_state, dtype = torch.float)
      # (n, x)

      if len(state.shape) == 1:
         # (1, x)
         state = torch.unsqueeze(state, 0)
         action = torch.unsqueeze(action, 0)
         reward = torch.unsqueeze(reward, 0)
         next_state = torch.unsqueeze(next_state, 0)
         
         game_over = (game_over, )

      # 1: predicted Q values with current state
      prediction = self.model(state)
      # print(prediction)

      target = prediction.clone()
      for indx in range(len(game_over)):
         Q_new = reward[indx]
         if not game_over[indx]:
            #Q_new = reward value + 0.9 * 
            Q_new = reward[indx] + self.gamma * torch.max(self.model(next_state[indx]))
            # print(Q_new)

         # print(target)
         target[indx][torch.argmax(action).item()] = Q_new
         # print(target)
         # target[0][0] = Q_new

      # 2: Q_new = r + y * max(next_predicted Q value) -> only do this if not done
      # prediction.clone()
      # predictions[argmax(action)] = Q_new
      self.optimizer.zero_grad()
      loss = self.criterion(target, prediction)
      loss.backward()

      self.optimizer.step()