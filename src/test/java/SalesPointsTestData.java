import ru.beeline.geo.model.SalesPointDo;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesPointsTestData {
    public static final int START_ID = 1000;

    public static final SalesPointDo POINT1 = new SalesPointDo(START_ID,
            "Собственный офис Билайн", "Москва", "улица Новослободская, 46");
    public static final SalesPointDo POINT2 = new SalesPointDo(START_ID + 1,
            "НОУ-ХАУ от Билайн", "Москва", "улица Новослободская, 4");
    public static final SalesPointDo POINT3 = new SalesPointDo(START_ID + 2,
            "Модуль связи Билайн", "Москва", "площадь Тверская застава, 7");
    public static final SalesPointDo POINT4 = new SalesPointDo(START_ID + 3,
            "Собственный офис Билайн", "Саратов", "улица Горная Б., 310А");
    public static final SalesPointDo POINT5 = new SalesPointDo(START_ID + 4,
            "Торговый модуль Билайн", "Саратов", "улица Зарубина, 167");
    public static final SalesPointDo POINT6 = new SalesPointDo(START_ID + 5,
            "Собственный офис Билайн", "Волгоград", "улица Комсомольская, 10");
    public static final SalesPointDo POINT7 = new SalesPointDo(START_ID + 5,
            "Собственный офис Билайн", "Волгоград", "улица Коммунистическая, 12");

    Map<String, SalesPointDo> salesPointsByCity = Stream.of(POINT1, POINT2, POINT3, POINT4, POINT5, POINT6, POINT7)
            .collect(Collectors.toMap(SalesPointDo::getCity, Function.identity()));
}
