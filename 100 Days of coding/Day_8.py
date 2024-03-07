import Data.caesar_art as art
alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

print(art.logo)
want_continue = True


def caesar(text, shift, direction):
    caesar_text = ""
    if direction == "decode":
        shift *= -1
    for letter in range(len(text)):
        symbol = text[letter]
        if symbol in alphabet:
            letter_index = (alphabet.index(text[letter]) + shift) % 26
            caesar_text += alphabet[letter_index]
        else:
            caesar_text += symbol
    print(caesar_text)
    return caesar_text


while want_continue:
    direction = input("Type 'encode' to encrypt, type 'decode' to decrypt:\n")
    text = input("Type your message:\n").lower()
    shift = int(input("Type the shift number:\n"))
    caesar(text, shift, direction)

    answer = input("Do you want to continue? ").lower()
    if (answer == "no"):
        want_continue = False
        print("For the Empreror, see you next time.")
