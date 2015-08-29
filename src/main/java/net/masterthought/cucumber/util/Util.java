package net.masterthought.cucumber.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import net.masterthought.cucumber.json.Tag;

public class Util {
    private static final PeriodFormatter TIME_FORMATTER = new PeriodFormatterBuilder()
            .appendDays()
            .appendSuffix("d")
            .appendSeparator(" ")
            .appendHours()
            .appendSuffix("h")
            .appendSeparator(" ")
            .appendMinutes()
            .appendSuffix("m")
            .appendSeparator(" ")
            .minimumPrintedDigits(2)
            .appendSeconds()
            .appendSuffix("s")
            .appendSeparator(" ")
            .minimumPrintedDigits(3)
            .appendMillis()
            .appendSuffix("ms")
            .toFormatter();

    public static String readFileAsString(String filePath) throws IOException {
    	return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static boolean itemExists(String item) {
        return item != null && !item.isEmpty();
    }

    public static boolean arrayNotEmpty(Tag[] tags) {
        return tags != null && tags.length != 0;
    }

    public static String passed(boolean value) {
        return value ? "<div class=\"passed\">" : "</div>";
    }

    public static String formatDuration(Long duration) {
        return TIME_FORMATTER.print(new Period(0, duration / 1000000));
    }

    public static int findStatusCount(List<Status> statuses, Status statusToFind) {
        int occurrence = 0;
        for (Status status : statuses) {
            if (status == statusToFind) {
                occurrence++;
            }
        }
        return occurrence;
    }

    public static String tagsToHtml(Tag[] tags) {
        String result = "<div class=\"feature-tags\"></div>";
        if (Util.arrayNotEmpty(tags)) {
            List<String> tagList = new ArrayList<>();
            for (Tag tag : tags) {
                String link = tag.getName().replace("@", "").trim() + ".html";
                String ref = "<a href=\"" + link + "\">" + tag.getName() + "</a>";
                tagList.add(ref);
            }
            result = "<div class=\"feature-tags\">" + StringUtils.join(tagList.toArray(), ",") + "</div>";
        }
        return result;
    }

}
