import random

letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
numbers = "0123456789"
symbols = "!#$%&()*+"

print("Welcome to the PyPassword Generator!")

# Input validation
nr_letters = max(
    0, int(input("How many letters would you like in your password?\n")))
nr_numbers = max(0, int(input("How many numbers would you like?\n")))
nr_symbols = max(0, int(input("How many symbols would you like?\n")))

whole_password_length = nr_letters + nr_symbols + nr_numbers
password = ""

for _ in range(whole_password_length):
    choice = random.randint(1, 3)

    if choice == 1 and nr_letters > 0:
        password += random.choice(letters)
        nr_letters -= 1
    elif choice == 2 and nr_numbers > 0:
        password += random.choice(numbers)
        nr_numbers -= 1
    elif choice == 3 and nr_symbols > 0:
        password += random.choice(symbols)
        nr_symbols -= 1

print(password)
