package cz.osu.kip.TTT.db;

public class DbException extends RuntimeException{
    public DbException(String message) {
        super(message);
    }

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }
}
