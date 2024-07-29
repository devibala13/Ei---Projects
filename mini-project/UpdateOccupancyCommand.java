public class UpdateOccupancyCommand implements Command {
    private int roomId;
    private int occupants;
    private Room room;

    public UpdateOccupancyCommand(int roomId, int occupants) {
        this.roomId = roomId;
        this.occupants = occupants;
        this.room = OfficeManager.getInstance().getRoom(roomId);
    }

    @Override
    public void execute() {
        if (room == null) {
            throw new IllegalArgumentException("Invalid room number. Please enter a valid room number.");
        }
        room.updateOccupancy(occupants);
    }

    @Override
    public void undo() {
        room.updateOccupancy(0); // Resetting to 0 occupancy.
    }
}
