package model;

public class PrintEventLog {
    public static void printEventLog() {
        StringBuilder sb = new StringBuilder();
        EventLog eventLog = EventLog.getInstance();

        for (Event event : eventLog) {
            sb.append(event.toString()).append("\n");
            sb.append("-----------------------------------\n");
        }

        if (sb.length() == 0) {
            sb.append("No events logged yet.");
        }

        System.out.println(sb);
    }
}
