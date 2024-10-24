# STOP N WAIT
import random
import time

class Sender:
    def __init__(self, packet_no):
        self.packet_no = packet_no
        self.sent = 0
        self.acknowledged = 0

class Receiver:
    def __init__(self, acknowledgement_no):
        self.acknowledgement_no = acknowledgement_no
        self.received = 0
        self.acknowledgement_sent = 0

def simulate_success_or_failure():
    return random.choice([True, False])

def main():
    n= int(input("enter no of frames"))

    senders = [Sender(i) for i in range(n)]
    receivers = [Receiver(i) for i in range(1, n+1)]

    # Sending and receiving the data packets
    for i in range(n):
        while True:
            print(f"\n\nNext Transmission")
            print(f"Sending PacketNo {i} from Sender")
            senders[i].sent = 1

            time.sleep(1)

            # Simulate receiving the packet
            if simulate_success_or_failure():
                receivers[i].received = 1
                print(f"Data PacketNo {i} successfully received.")
                receivers[i].acknowledgement_sent = 1
                print(f"Sending Acknowledgement No {i + 1} to Sender")

                time.sleep(1)  # Simulating a delay

                if simulate_success_or_failure():
                    senders[i].acknowledged = 1
                    print(f"Acknowledgement for PacketNo {i} Received")
                    break
                else:
                    senders[i].acknowledged = 0
                    print(f"Acknowledgement for PacketNo {i} lost.")
                    print("Process ended due to acknowledgment loss.")
                    return
            else:
                receivers[i].received = 0
                print(f"Data PacketNo {i} lost in transmission.")
                continue  # Resend the packet in the next iteration

if __name__ == "__main__":
    main()
