# SELECETIVE REPEAT
import random

def selective_repeat_transmission(i, N, tf):
    tt = 0  # Total frames sent and resent
    ack = [False] * (tf + 1)  # List to keep track of which frames are acknowledged

    while i <= tf:
        for k in range(i, min(i + N, tf + 1)):
            if not ack[k]:
                print(f"Sending Frame {k}")
                tt += 1  # Increase the count of total transmissions

        for k in range(i, min(i + N, tf + 1)):
            if not ack[k]:
                f = random.randint(0, 1)  # Simulate acknowledgment success/failure
                if f == 0:
                    print(f"Acknowledgment for Frame {k}")
                    ack[k] = True  # Mark frame as acknowledged
                else:
                    print(f"Timeout!! Frame Number: {k} Not Received")

        print()

        while i <= tf and ack[i]:
            i += 1  # Move the window forward for acknowledged frames

    return tt

def main():
    tf = int(input("Total number of frames: "))
    N = int(input("Window Size: "))
    i = 1
    tt = selective_repeat_transmission(i, N, tf)
    print(f"Total number of frames which were sent and resent are: {tt}")

if __name__ == "__main__":
    main()
