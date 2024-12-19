import pygame
import random
from enum import Enum
from collections import namedtuple

pygame.init()
font = pygame.font.Font('arial.ttf', 25)

Bird = namedtuple('Point', 'height, velocity')
Pipe = namedtuple('Pipe', 'position, opening')

# rgb colors
WHITE = (255, 255, 255)
GREEN = (0, 200, 0)
YELLOW = (235, 235, 0)
ORANGE = (255, 135, 0)
BLUE = (50, 255, 255)
BLACK = (0, 0, 0)

PIPE_WIDTH = 50
ACCELERATION = -.075
SPEED = 100

class FlappyBird:

   def __init__(self, w=840, h=480):
      self.w = w
      self.h = h
      # init display
      self.display = pygame.display.set_mode((self.w, self.h))
      pygame.display.set_caption('Flappy Bird')
      self.clock = pygame.time.Clock()
      
      # init game state      
      self.bird = Bird(240, 0)
      self.pipes = [Pipe(440, 240), Pipe(740, 380), Pipe(1040, 100), Pipe(1340, 160)]      
      self.score = 0

   def _move(self):
      height = self.bird.height - self.bird.velocity
      velocity = self.bird.velocity + ACCELERATION
      
      self.bird = Bird(height, velocity)

      for indx in range(len(self.pipes)):
         pos = self.pipes[indx].position - 1
         opening = self.pipes[indx].opening
         self.pipes[indx] = Pipe(pos, opening)

      if self.pipes[0].position == 0 - PIPE_WIDTH: 
         self.pipes.pop(0)
         self.pipes.append(Pipe(self.pipes[-1].position + random.randint(200, 300), random.randint(100, 430)))

   def _is_collision(self):
      # bird is out of bounds
      if self.bird.height < 0 or self.bird.height > self.h:
         return True
      # bird hits front of pipe
      elif self.pipes[0].position == 94 and (self.bird.height > self.pipes[0].opening + 45 or self.bird.height < self.pipes[0].opening - 45): 
         return True
      # bird hits middle of pipe
      elif (self.pipes[0].position < 92 and self.pipes[0].position >= 17) and (self.bird.height + 12.5 > self.pipes[0].opening + 45 or self.bird.height - 12.5 < self.pipes[0].opening - 45): 
         return True
      return False 

   def _update_ui(self):
      self.display.fill(BLUE)
      
      for pipe_object in self.pipes:
         # top pipe
         pygame.draw.rect(self.display, GREEN, pygame.Rect(pipe_object.position, 0, PIPE_WIDTH, pipe_object.opening - 45))
         pygame.draw.rect(self.display, GREEN, pygame.Rect(pipe_object.position - 5, pipe_object.opening - 65, PIPE_WIDTH + 10, 20))
         # bottom pipe
         pygame.draw.rect(self.display, GREEN, pygame.Rect(pipe_object.position, pipe_object.opening + 45, PIPE_WIDTH, self.h - pipe_object.opening - 45))
         pygame.draw.rect(self.display, GREEN, pygame.Rect(pipe_object.position - 5, pipe_object.opening + 45, PIPE_WIDTH + 10, 20))
         
      # bird
      pygame.draw.circle(self.display, YELLOW, (80, self.bird.height), 12.5)
      pygame.draw.circle(self.display, BLACK, (83 , self.bird.height - 5), 2)
      pygame.draw.polygon(self.display, ORANGE, [[86, self.bird.height - 5], [86, self.bird.height + 5], [94, self.bird.height]])

      # score words
      text = font.render(" Score: " + str(self.score), True, BLACK)
      self.display.blit(text, [0, 0])
      pygame.display.flip()

   def play_step(self):
      # 1. collect user input
      for event in pygame.event.get():
         if event.type == pygame.QUIT:
            pygame.quit()
            quit()
         if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
               height = self.bird.height
               self.bird = Bird(height, 2.25)
      
      # 2. move
      self._move() # update the bird and pipe positions
      
      # 3. check if game over
      game_over = False
      if self._is_collision():
         game_over = True
         return game_over, self.score
         
      # 4. update score
      if self.pipes[0].position == 80 - 12 - PIPE_WIDTH:
         self.score += 1
      
      # 5. update ui and clock
      self._update_ui()
      self.clock.tick(SPEED + self.score)
      # 6. return game over and score
      return game_over, self.score
   
if __name__ == '__main__':
   game = FlappyBird()
   
   # game loop
   while True:
      game_over, score = game.play_step()
      
      if game_over == True:
         break
      
   print('Final Score:', score)
      
      
   pygame.quit()