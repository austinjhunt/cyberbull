package edu.vanderbilt.cs.cyberbull.core.activity;

import java.time.LocalDateTime;

public interface Operation {
    public String getDateTimeFormatted();
    public LocalDateTime getDateTime();
    public String getAction();
}
