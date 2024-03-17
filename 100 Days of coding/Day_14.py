import random
from Data.higher_lower_art import logo, vs
from Data.higher_lower_data import data

score = 0


def round():
    global score
    compare_a = random.randint(0, len(data)-1)
    compare_b = random.randint(0, len(data)-1)

    while compare_b == compare_a:
        compare_b = random.randint(0, len(data)-1)

    print(logo)
    print(f"Current score: {score}")
    print(f"Compare A: {data[compare_a].get('name')}, a {
          data[compare_a].get('description')}, from {data[compare_a].get('country')}")

    print(vs)
    print(f"Compare B: {data[compare_b].get('name')}, a {
          data[compare_b].get('description')}, from {data[compare_b].get('country')}")

    player_choice = input("Who has more followers? Type 'A' or 'B': ").lower()

    if player_choice == 'a':
        return data[compare_a].get('follower_count') > data[compare_b].get('follower_count')
    elif player_choice == 'b':
        return data[compare_b].get('follower_count') > data[compare_a].get('follower_count')


def game():
    global score
    while round() != False:
        score += 1

    print(f"You lost, your final score is {score}")


game()
