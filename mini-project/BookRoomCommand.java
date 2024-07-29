public class BookRoomCommand implements Command {
    private int roomId;
    private String startTime;
    private int duration;
    private Room room;

    public BookRoomCommand(int roomId, String startTime, int duration) {
        this.roomId = roomId;
        this.startTime = startTime;
        this.duration = duration;
        this.room = OfficeManager.getInstance().getRoom(roomId);
    }

    @Override
    public void execute() {
        if (room == null) {
            throw new IllegalArgumentException("Invalid room number. Please enter a valid room number.");
        }
        room.bookRoom(startTime, duration);
    }

    @Override
    public void undo() {
        room.cancelBooking();
    }
}
