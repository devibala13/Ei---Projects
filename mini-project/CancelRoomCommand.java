public class CancelRoomCommand implements Command {
    private int roomId;
    private Room room;

    public CancelRoomCommand(int roomId) {
        this.roomId = roomId;
        this.room = OfficeManager.getInstance().getRoom(roomId);
    }

    @Override
    public void execute() {
        if (room == null) {
            throw new IllegalArgumentException("Invalid room number. Please enter a valid room number.");
        }
        room.cancelBooking();
    }

    @Override
    public void undo() {
        // Undo logic for cancellation, if any.
    }
}
