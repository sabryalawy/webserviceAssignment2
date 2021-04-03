package config;

public class RoomCustomerID {
    int roomId;
    int customerId;

    public RoomCustomerID(int roomId, int customerId) {
        this.roomId = roomId;
        this.customerId = customerId;
    }

    public RoomCustomerID() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
