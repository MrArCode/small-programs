import random

rock = """
    _______
---'   ____)
      (_____)
      (_____)
      (____)
---.__(___)
"""

paper = """
    _______
---'   ____)____
          ______)
          _______)
         _______)
---.__________)
"""

scissors = """
    _______
---'   ____)____
          ______)
       __________)
      (____)
---.__(___)
"""

player_choice = int(input("Choose your number, 1 - rock, 2 - paper, 3 - scissors "))
computer_choice = random.randint(1, 3)
possibilities = [rock, paper, scissors]

print(possibilities[player_choice - 1])
print(f"Computer chose\n {possibilities[computer_choice - 1]}")

if player_choice == computer_choice:
    print("It's a draw!")
elif (
    (player_choice == 1 and computer_choice == 3)
    or (player_choice == 2 and computer_choice == 1)
    or (player_choice == 3 and computer_choice == 2)
):
    print("You win!")
else:
    print("You lose!")
