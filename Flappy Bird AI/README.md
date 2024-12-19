# Flappy Bird AI

### Description
The **Flappy Bird AI** is a reinforcement learning-based AI designed to play a custom-built Flappy Bird game. This project is an adaptation of the AI from the tutorial ["Python + PyTorch + Pygame Reinforcement Learning – Train an AI to Play Snake"](https://www.youtube.com/watch?v=L8ypSXwyBds) by freeCodeCamp.org, with modifications to fit the mechanics of the Flappy Bird game created using Pygame. The AI learns to play the game through trial and error and can be trained with customizable parameters.

---

## Features
   - **Custom Flappy Bird Game**: The Flappy Bird game is homemade using Pygame, with customizable game elements like pipe generation, bird behavior, and more.
   - **AI Training**: Uses PyTorch to train the AI to play Flappy Bird using reinforcement learning techniques.
   - **Growth Visualization**: Includes a graph with a line of best fit that tracks the AI's progress and growth over time.
   - **Training Parameters**: The training process is customizable, including learning rate, epochs, and other hyperparameters for the AI.

---

## Prerequisites
To run the script:
1. **Python 3.x Installed**: [Download Python](https://www.python.org/downloads/)
2. **Dependencies Installed**:
   ```bash
   pip install torch pygame matplotlib numpy
   ```
3. **IPython Installed**: For displaying the AI's progress graph during training.
   ```bash
   pip install ipython
   ```

---

## Installation
1. Clone the Clone the repository:
   ```bash
   git clone https://github.com/Mikah-Schueller/Flappy-Bird-AI.git
   ```
2. Navigate to the project directory:
   ```bash
   cd flappy-bird-ai
   ```
3. Install required libraries:
   ```bash
   pip install torch pygame matplotlib numpy ipython
   ```

---

## Usage Instructions
1. Run the Script:
   - Open the agent.py file and run the script in your Python environment.
   - The script will start training the AI to play the Flappy Bird game.
   - The AI's performance will be tracked and displayed in real-time, and a graph showing its growth will be generated.
2. Training Parameters:
   - You can adjust various training parameters such as the learning rate, number of epochs, and reward system to experiment with the AI's performance.
   - The graph's appearance can also be customized.
3. Post-Training:
   - Once training is complete, the AI will be capable of playing the Flappy Bird game with a learned strategy.

---

## Customization
- **Training Parameters**: Modify the hyperparameters such as learning rate and number of epochs for different training results.
- **Flappy Bird Game**: Customize game elements like pipe speed, bird mechanics, and more.
- **Graph Display**: You can modify the appearance and functionality of the growth graph, including the line of best fit. 

---

## Acknowledgments
This project is inspired by the tutorial ["Python + PyTorch + Pygame Reinforcement Learning – Train an AI to Play Snake"](https://www.youtube.com/watch?v=L8ypSXwyBds) by freeCodeCamp.org, with significant modifications made for the Flappy Bird game and other enhancements.

---

## Disclaimer
This project is intended for educational purposes to demonstrate reinforcement learning and game development. Feel free to modify and experiment with the code to suit your needs.
