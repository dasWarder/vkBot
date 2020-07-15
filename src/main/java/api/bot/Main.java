package api.bot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final String ACCESS_TOKEN = "f17844f0537664289f20204d6a33ad45ec1079a68cf2011989264529477946b6f5f70c911a38b28b1e660";

    public static void main(String[] args) {

        Group group = new Group(197144715, ACCESS_TOKEN);

        group.onSimpleTextMessage(message -> {
                Message toSend = new Message()
                        .from(group)
                        .to(message.authorId());
            LocalDateTime ldt = LocalDateTime.now();
            if ((DayOfWeek.MONDAY.equals(ldt.getDayOfWeek())
                    && ((ldt.getHour() > 18)
                    && (ldt.getHour() <= 23))) ||
                    (DayOfWeek.WEDNESDAY.equals(ldt.getDayOfWeek())
                            && ((ldt.getHour() > 11)
                            && (ldt.getHour() <= 23)))) {
                toSend.text("У нас работает чат-бот, поэтому для получения информации введите:" + "\n"
                        + "/работа - для получения графика работы" + "\n" +
                "/расписание - для того, чтобы получить рассписание" + "\n" +
                        "/праздники - получение списка праздников").send();
            }
        });


        group.onCommand("/работа", message -> {
             new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Мы работаем во вторник, четверг и иногда в субботу с 12 до 18." + "\n"
                            + "Для более подробной информации про рассписание введите /расписание")
                    .send();
        });

        group.onCommand("/расписание", message -> {
            new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("11:30 - выезжает автобус на хутор" + "\n"
                            + "12:00 - 15:30 - занятия по расписанию"+ "\n"
                            + "15:30 - 16:00 - обед" + "\n"
                            + "16:00 - 18:00 - занятия по расписанию" + "\n"
                            + "18:00 - автобус заберает всех домой" + "\n")
                    .send();
        });

        group.onCommand("/праздники", message -> {
            new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Любое воскресенье для нас праздник" + "\n")
                    .send();
        });

    }
}
