import random
from Data.guess_game_art import logo

difficulty = ""
chances = 0
computer_number = 0
player_guess = 0


def introduction():
    print(logo)
    print("Welcom to The Number Guessing Game!")
    print("I'm thinking of a number between 1 to 100.")

    global computer_number
    computer_number = random.randint(1, 100)


def choose_dificulty():
    global difficulty
    global chances
    difficulty = input("Choose a difficulty. Type 'easy' or 'hard': ").lower()

    while difficulty != "easy" and difficulty != "hard":
        print("Wrong input")
        difficulty = input(
            "Choose a difficulty. Type 'easy' or 'hard': ").lower()

    if difficulty == "easy":
        chances = 10
    else:
        difficulty = "hard"
        chances = 5


def reset():
    global difficulty
    global chances
    global computer_number
    global player_guess
    difficulty = ""
    chances = 0
    computer_number = 0
    player_guess = 0


def game():
    global player_guess
    global chances
    global computer_number

    introduction()
    choose_dificulty()

    while chances > 0:
        print(f"You have {chances} to guess the number.")
        player_guess = int(input("Make a guess: "))

        if (player_guess == computer_number):
            print("You win!!!")
            play_again = input(
                "Do you want to play again? Type 'yes' or 'no'").lower()
            if play_again == "yes":
                reset()
                game()
            else:
                print("Bye bye")
                break
        elif player_guess > computer_number:
            print("Too high.")
            print("Guess again.")
            chances -= 1
        elif player_guess < computer_number:
            print("Too low.")
            print("Guess again.")
            chances -= 1

        print()

    print("You lose.")
    print(f"The number was {computer_number}")
    play_again = input(
        "Do you want to play again? Type 'yes' or 'no' ").lower()
    if play_again == "yes":
        reset()
        game()
    elif play_again == "no":
        print("Bye bye")


game()
