package week3.Hotel;

import java.io.PrintStream;

/**
 * Hotel;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.321397129
 */

public class Hotel {
	// ------------------ Instance variables ----------------
	private Room room1;
	private Room room2;
	private Password pass;

	private String hotelName;

	// ------------------ Constructor ------------------------

	public Hotel(String name) {
		room1 = new Room(101, new Safe());
		room2 = new PricedRoom(102, 240, 10);
		hotelName = name;
		pass = new Password();

	}

	// ------------------ Commands ------------------------

	// @ ensures \result == getFreeRoom();

	/**
	 * Checks in the guest
	 * 
	 * @param result
	 *            Return the room the guest checked in or null if not checked
	 *            in;
	 */

	public Room checkIn(String password, String guestname) {
		Room result = null;
		Guest guest = new Guest(guestname);
		if (pass.testWord(password) == true && guest.getRoom() == null) {
			if (getFreeRoom() == room1) {
				guest.checkin(room1);
				result = room1;
			} else if (getFreeRoom() == room2) {
				guest.checkin(room2);
				result = room2;
			}
		}

		return result;
	}

	/**
	 * Checks out the guest
	 * 
	 * @param result
	 *            Return if succesful;
	 */

	// @ ensures getFreeRoom() != null ==> \result == true;
	public boolean checkOut(String guestname) {
		boolean result = false;
		if (guestname != null) {
			Room guestroom = getRoom(guestname);
			if (guestroom != null) {
				guestroom.getGuest().checkout();
				guestroom.getSafe().deactivate();
				result = true;
			}
		}
		return result;
	}
	// ------------------ Queries --------------------------

	/**
	 * Returns a free room
	 * 
	 * @param result
	 *            A free room;
	 */

	// @ pure;
	public Room getFreeRoom() {
		Room result = null;
		if (room1.getGuest() == null) {
			result = room1;
		} else if (room2.getGuest() == null) {
			result = room2;
		}

		return result;
	}

	/**
	 * Returns the current room of the guest
	 * 
	 * @param result
	 *            The room of the guest;
	 */

	// @ pure;
	public Room getRoom(String guestname) {
		Room result = null;

		if (room1.getGuest() != null && room1.getGuest().getName().equals(guestname)) {
			result = room1;
		} else if (room2.getGuest() != null && room2.getGuest().getName().equals(guestname)) {
			result = room2;
		}

		return result;
	}

	// @ pure;
	/**
	 * Shows the current password of the Hotel;
	 * 
	 * @param passo
	 *            The password of the safe;
	 */

	public Password getPassword() {
		return pass;
	}

	// @ pure;
	/**
	 * Shows the current information of the Hotel;
	 * 
	 * @param result
	 *            The String of the information;
	 */

	public String toString() {
		String result = "Hotel: " + hotelName + " room 101: " + room1.getGuest() + " Status of the safe: "
				+ room1.getStateSafe() + " room 102: " + room2.getGuest() + " Status of the safe: "
				+ room2.getStateSafe();
		return result;
	}

	/**
	 * Creates and closes a bill that uses the guest name amount of nights and
	 * the outputstream
	 */

	public Bill getBill(String guestName, int nights, PrintStream out) {

		Bill bill = new Bill(out);
		Room room = this.getRoom(guestName);
		if (room != null && room instanceof PricedRoom) {
			PricedRoom pricedRoom = (PricedRoom) room;
			int i = 0;
			while (i <= nights) {
				bill.newItem(pricedRoom);
				if(pricedRoom.getSafe() instanceof PricedSafe){
					bill.newItem((PricedSafe) pricedRoom.getSafe());
				}
				i++;
			}
			return bill;
		}
		return null;
	}
}
