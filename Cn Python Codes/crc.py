# CRC
def divide(dividend, divisor):
    n = len(divisor)
    m = len(dividend)
    temp = dividend.copy()

    for i in range(m - n + 1):
        if temp[i] == 1:
            for j in range(n):
                temp[i + j] ^= divisor[j]

    return temp[-(n - 1):]

def check_error(received, divisor):
    remainder = divide(received, divisor)
    return any(remainder)

def sender():
    data = [1, 0, 0, 0, 1, 0, 0]
    divisor = [1, 1, 0, 1, 0]
    print("Original Data:", ''.join(map(str, data)))
    print("Divisor:", ''.join(map(str, divisor)))

    data += [0] * (len(divisor) - 1) # Append zeros

    remainder = divide(data, divisor)
    code = data[:-(len(divisor) - 1)] + remainder

    print("Data sent with CRC appended:", ''.join(map(str, code)))
    print("CRC remainder:", ''.join(map(str, remainder)))

    return code, divisor

def receiver(sent_data, divisor):
    received_input = input("Enter the received data with CRC appended: ")
    received_data = [int(bit) for bit in received_input]

    if check_error(received_data, divisor):
        print("Errors found in the received data.")
    else:
        print("No errors found in the received data.")


print("\nSender Side")
sent_data, divisor = sender()

print("\nReceiver Side")
receiver(sent_data, divisor)
