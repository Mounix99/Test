package sample;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Main {
    public static void main(String[] arg) throws IOException {
        List<Lesson> lessonList = new ArrayList<> ();
        List<Time> timeList = new ArrayList<>();

        Document doc = Jsoup.connect("https://www.udemy.com/course/learn-flutter-dart-to-build-ios-android-apps/").get();

        Elements divFirstElements = doc.getElementsByAttributeValue("class", "lecture-container lecture-container--preview");

        divFirstElements.forEach( divFirstElement-> {

            Element aElement = divFirstElement.tagName("a");
            String title = aElement.text();

            String video = takeLast(title, 5);

            timeList.add(new Time(video));

            lessonList.add(new Lesson(title));
        });

        lessonList.forEach(System.out::println);
        timeList.forEach(System.out::println);

    }

    private static String takeLast(String value, int count) {
        if (value == null || value.trim().length() == 0) return "";
        if (count < 1) return "";

        if (value.length() > count) {
            return value.substring(value.length() - count);
        } else {
            return value;
        }
    }

    static class Time{
        private String time;

        public Time(String  time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String  time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Time{" +
                    "time=" + time +
                    '}';
        }
    }

    static class Lesson{
       private String title;

        Lesson(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Lesson" +
                    //"time='" + time + '\'' +
                    " " + title + '\'' ;
        }
    }
}

