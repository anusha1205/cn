def bin_sum(chunk1, chunk2, div):
    sum_val = int(chunk1, 2) + int(chunk2, 2)
    st = bin(sum_val)[2:]

    if len(st) == div + 1:
        carry = "1"
        st1 = st[1:]
        st = bin_sum(st1, carry, div)
        return st
    else:
        return st.zfill(div)

def find_checksum(message, div):
    temp = message[:div]
    message = message[div:]
    for _ in range(len(message) // div):
        temp = bin_sum(temp, message[:div], div)
        message = message[div:]

    check_sum = temp
    check_sum_inverted = ''.join('1' if c == '0' else '0' for c in check_sum)
    return check_sum_inverted

def padding_zeros(message, div):
    if len(message) % div != 0:
        message += '0' * (div - len(message) % div)
    return message

def main():
    send_message = input("Enter sender message in binary (without spaces): ")
    send_message_padded = padding_zeros(send_message, 8)
    sender_checksum = find_checksum(send_message_padded, 8)
    
    print(f"\nSender message binary: {send_message}")
    print(f"Checksum of sender: {sender_checksum}")

    rec_message = input("\nEnter received message in binary (without spaces): ")
    rec_message_padded = padding_zeros(rec_message, 8)
    rec_checksum = find_checksum(rec_message_padded, 8)

    print(f"\nReceived message binary: {rec_message}")
    print(f"Checksum of receiver: {rec_checksum}")

    if sender_checksum == rec_checksum:
        print("\nNo error detected.")
    else:
        print("\nError detected.")

if __name__ == "__main__":
    main()
