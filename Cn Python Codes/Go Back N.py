# GO BACK N
import random

def transmission(i, N, tf):
    tt = 0
    while i <= tf:
        z = 0
        for k in range(i, min(i + N, tf + 1)):
            print(f"Sending Frame {k}")
            tt += 1

        for k in range(i, min(i + N, tf + 1)):
            f = random.randint(0, 1)
            if f == 0:
                print(f"Acknowledgment for Frame {k}")
                z += 1
            else:
                print(f"Timeout!! Frame Number : {k} Not Received")
                print("Retransmitting Window")
                break

        print()
        i += z

    return tt

def main():
    tf = int(input("Total number of frames: "))
    N = int(input("Window Size: "))
    i = 1
    tt = transmission(i, N, tf)
    print(f"Total number of frames which were sent and resent are: {tt}")

if __name__ == "__main__":
    main()
