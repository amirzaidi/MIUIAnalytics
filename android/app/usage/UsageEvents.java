package android.app.usage;

public final class UsageEvents {

    public static final class Event {
        public static final int MOVE_TO_BACKGROUND = 2;
        public static final int MOVE_TO_FOREGROUND = 1;
        public static final int NONE = 0;

        public String getPackageName() {
            throw new RuntimeException("Stub!");
        }

        public String getClassName() {
            throw new RuntimeException("Stub!");
        }

        public long getTimeStamp() {
            throw new RuntimeException("Stub!");
        }

        public int getEventType() {
            throw new RuntimeException("Stub!");
        }
    }

    public boolean hasNextEvent() {
        throw new RuntimeException("Stub!");
    }

    public boolean getNextEvent(Event event) {
        throw new RuntimeException("Stub!");
    }
}
