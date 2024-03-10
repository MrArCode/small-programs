# import random
# from Data.blackjack_art import logo

# cards = [11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10]


# def game():
#     want_play = input(
#         "Do you want to play a game of Blackjack? Type 'y' or 'n': ").lower()
#     while want_play == 'y':
#         print(logo)

#         player_cards = []
#         computer_cards = []

#         player_cards.append(random.choice(cards))
#         player_cards.append(random.choice(cards))
#         sum_of_player_card = 0

#         computer_cards.append(random.choice(cards))
#         computer_cards.append(random.choice(cards))
#         sum_of_computer_cards = 0

#         print(f"Your cards: {player_cards}")
#         print(f"Computer's first card: {computer_cards[0]}")

#         want_another_card = input(
#             "Type 'y' to get another card, type 'n' to pass: ").lower()
#         if want_another_card == 'y':
#             player_cards.append(random.choice(cards))

#         for num in player_cards:
#             sum_of_player_card += num

#         if sum_of_player_card > 21:
#             print("You lose")
#             game()

#         for num in computer_cards:
#             sum_of_computer_cards += num
#         while sum_of_computer_cards < 17:
#             computer_cards.append(random.choice(cards))
#             for num in computer_cards:
#                 sum_of_computer_cards += num

#         print(f"Your final hand: {player_cards}")
#         print(f"Computer final hand: {computer_cards}")

#         if 21 - sum_of_player_card < (21 - sum_of_computer_cards)*-1:
#             print("You win")
#         else:
#             print("You lose")

#         want_play = input(
#             "Do you want to play a game of Blackjack? Type 'y' or 'n': ").lower()


# game()
# print("Bye bye")


import random
from Data.blackjack_art import logo


def deal_card():
    cards = [11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10]
    card = random.choice(cards)
    return card


def calculate_score(cards):
    if sum(cards) == 21 and len(cards) == 2:
        return 0

    if 11 in cards and sum(cards) > 21:
        cards.remove(11)
        cards.append(1)
    return sum(cards)


def compare(user_score, computer_score):
    if user_score > 21 and computer_score > 21:
        return "You went over. You lose 😤"

    if user_score == computer_score:
        return "Draw 🙃"
    elif computer_score == 0:
        return "Lose, opponent has Blackjack 😱"
    elif user_score == 0:
        return "Win with a Blackjack 😎"
    elif user_score > 21:
        return "You went over. You lose 😭"
    elif computer_score > 21:
        return "Opponent went over. You win 😁"
    elif user_score > computer_score:
        return "You win 😃"
    else:
        return "You lose 😤"


def play_game():

    print(logo)

    user_cards = []
    computer_cards = []
    is_game_over = False

    for _ in range(2):
        user_cards.append(deal_card())
        computer_cards.append(deal_card())

    while not is_game_over:
        user_score = calculate_score(user_cards)
        computer_score = calculate_score(computer_cards)
        print(f"   Your cards: {user_cards}, current score: {user_score}")
        print(f"   Computer's first card: {computer_cards[0]}")

        if user_score == 0 or computer_score == 0 or user_score > 21:
            is_game_over = True
        else:
            user_should_deal = input(
                "Type 'y' to get another card, type 'n' to pass: ")
            if user_should_deal == "y":
                user_cards.append(deal_card())
            else:
                is_game_over = True

    while computer_score != 0 and computer_score < 17:
        computer_cards.append(deal_card())
        computer_score = calculate_score(computer_cards)

    print(f"   Your final hand: {user_cards}, final score: {user_score}")
    print(f"   Computer's final hand: {
          computer_cards}, final score: {computer_score}")
    print(compare(user_score, computer_score))


while input("Do you want to play a game of Blackjack? Type 'y' or 'n': ") == "y":
    play_game()
