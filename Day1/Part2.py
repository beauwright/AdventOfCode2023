file = "./resources/puzzleinput.txt"

numbers_spelled_out = ["one", "two", "three", "four", "five", "six", "seven",
                       "eight", "nine"]

numbers_to_digits = {
    "one": 1,
    "two": 2,
    "three": 3,
    "four": 4,
    "five": 5,
    "six": 6,
    "seven": 7,
    "eight": 8,
    "nine": 9
}


def find_first_number_or_digit(line):
    first_digit = find_first_digit(line)
    first_number = find_first_number(line)
    if first_digit.get("index") is not None and first_number.get("index") is not None:
        if first_number.get("index") < first_digit.get("index"):
            return first_number.get("char")
        else:
            return first_digit.get("char")
    if first_number.get("index") is None:
        return first_digit.get("char")
    if first_digit.get("index") is None:
        return first_number.get("char")


def find_last_number_or_digit(line):
    last_digit = find_last_digit(line)
    last_number = find_last_number(line)
    if last_digit.get("index") is not None and last_number.get("index") is not None:
        if last_number.get("index") > last_digit.get("index"):
            return last_number.get("char")
        else:
            return last_digit.get("char")
    if last_number.get("index") is None:
        return last_digit.get("char")
    if last_digit.get("index") is None:
        return last_number.get("char")


def find_first_number(line):
    first_number_index = None
    first_number_digit = None

    for number in numbers_spelled_out:
        temp_result = line.find(number)
        if temp_result != -1:
            if first_number_index is None:
                first_number_index = temp_result
                first_number_digit = numbers_to_digits.get(number)
            else:
                if first_number_index > temp_result:
                    first_number_index = temp_result
                    first_number_digit = numbers_to_digits.get(number)
    return {"char": first_number_digit, "index": first_number_index}


def find_last_number(line):
    last_number_index = None
    last_number_digit = None
    for number in numbers_spelled_out:
        temp_result = line.rfind(number)
        if temp_result != -1:
            if last_number_index is None:
                last_number_index = temp_result
                last_number_digit = numbers_to_digits.get(number)
            else:
                if last_number_index < temp_result:
                    last_number_index = temp_result
                    last_number_digit = numbers_to_digits.get(number)
    return {"char": last_number_digit, "index": last_number_index}


def find_first_digit(line):
    index = -1
    for char in line:
        index += 1
        if char.isdigit():
            return {"char": char, "index": index}
    return {"char": None, "index": None}


def find_last_digit(line):
    index = -1
    char_return = None
    char_return_index = None
    for char in line:
        index += 1
        if char.isdigit():
            char_return = char
            char_return_index = index
    return {"char": char_return, "index": char_return_index}


with open(file, 'r') as file_contents:
    lines = file_contents.readlines()

sum = 0
for line in lines:
    first_val = find_first_number_or_digit(line)
    last_val = find_last_number_or_digit(line)
    concat_first_and_last = f"{first_val}{last_val}"
    sum += int(concat_first_and_last)

print(sum)
