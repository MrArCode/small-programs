print("Welcome to the Tip Calculator!")
bill = float(input("What was the total bill? $"))
tip = int(input("What percentage would you like to give? 10, 12 or 15? ")) / 100
amount_of_people = int(input("How many people to split the bill? "))
each_person_pay = round((bill * (1 + tip)) / amount_of_people, 2)
print(f"Each person should pay ${each_person_pay}")
