from Data.auction_art import logo

print(logo)
print("Welcome to the secret auction program.")

continue_bidding = True
list_of_player_and_bids = {}

while continue_bidding:
    name = input("What is your name?: ")
    bid = int(input("What is your bid?: $ "))
    other_bidders = input(
        "Are there any other bidders? Type 'yes' or 'no': ").lower()

    list_of_player_and_bids[name] = bid

    if other_bidders == "no":
        continue_bidding = False

the_highest_bid = 0
winner = ""

for name_of_player in list_of_player_and_bids:
    if list_of_player_and_bids[name_of_player] > the_highest_bid:
        the_highest_bid = list_of_player_and_bids[name_of_player]
        winner = name_of_player

print(f"{winner} wins, with a bid of ${the_highest_bid}.")
