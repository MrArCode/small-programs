MENU = {
    "espresso": {
        "ingredients": {
            "water": 50,
            "coffee": 18,
        },
        "cost": 1.5,
    },
    "latte": {
        "ingredients": {
            "water": 200,
            "milk": 150,
            "coffee": 24,
        },
        "cost": 2.5,
    },
    "cappuccino": {
        "ingredients": {
            "water": 250,
            "milk": 100,
            "coffee": 24,
        },
        "cost": 3.0,
    }
}

resources = {
    "water": 300,
    "milk": 200,
    "coffee": 100,
    "money": 0
}

action = 'on'
client_money = 0


def resources_show():
    print(f"Water: {resources["water"]}")
    print(f"Milk: {resources["milk"]}")
    print(f"Coffee: {resources["coffee"]}")
    print(f"Money: ${resources["money"]}")


def insert_coin():
    print("Please instert coins.")
    pennies_worth = int(input("How many pennies?: ")) * 0.01
    nickles_worth = int(input("How many nickles?: ")) * 0.05
    dimes_worth = int(input("How many dimes?: ")) * 0.10
    quarters_worth = int(input("How many quarters?: ")) * 0.25

    total_amount = round((pennies_worth + nickles_worth +
                         dimes_worth + quarters_worth), 2)
    return total_amount


def make_coffee(action):
    global client_money
    if resources["coffee"] < MENU[action]["ingredients"]["coffee"]:
        return print("Sorry not enouht coffe")
    elif resources["milk"] < MENU[action]["ingredients"]["milk"]:
        return print("Sorry not enouht milk")
    elif resources["water"] < MENU[action]["ingredients"]["water"]:
        return print("Sorry not enouht water")
    else:
        print(f"Here is ${client_money - MENU[action]["cost"]} in change")
        print(f"Here is your {action}")
        resources["money"] += MENU[action]["cost"]
        resources["milk"] -= MENU[action]["ingredients"]["milk"]
        resources["coffee"] -= MENU[action]["ingredients"]["coffee"]
        resources["water"] -= MENU[action]["ingredients"]["water"]


while action != 'off':
    action = input(
        "What would you like? (espresso/latte/cappuccino): ").lower()

    if action == 'raport':
        resources_show()
    elif action in ['espresso', 'latte', 'cappuccino']:
        client_money = insert_coin()
        if client_money < MENU[action]["cost"]:
            print(f"Sorry that's not enough money. Money refunded {
                  client_money}$.")
            resources["money"] = 0
            client_money = 0
            continue
        make_coffee(action)
