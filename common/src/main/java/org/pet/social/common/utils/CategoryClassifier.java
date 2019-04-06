package org.pet.social.common.utils;

import org.pet.social.common.enums.GOVERMANT_STRUCTURE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CategoryClassifier {
    private static Map<GOVERMANT_STRUCTURE, String[]> keywords;

    private static final String[] MVD_KEYWORDS = {"кража", "разбой", "ограбление", "вор", "ворует", "преступник", "стащил", "украл", "пропажа"};
    private static final String[] GIBDD_KEYWORDS = {"паркова", "газон", "обгон", "пдд", "превышение", "обгонщик", "встречка", "встречная", "полоса", "двойная", "сплошная"};
    private static final String[] FSB_KEYWORDS = {"бомба", "терроризм", "террорист", "терракт", "ислам", "исламисты", "донбас", "шпионы", "шпион", "слежка"};
    private static final String[] PROKURATURA_KEYWORDS = {"нарушение", "должностное", "должность", "превышение", "поломочий", "полномочия", "бездействие"};
    private static final String[] ROAD_SERVICE_KEYWORDS = {"яма", "дыра", "дырка", "дорога", "дороги", "разбит", "выбоина", "траса", "путь"};
    private static final String[] ZHKH_KEYWORDS = {"устарел", "ветхий", "разваливается", "обваливается", "дом", "крыша", "штукатурка", "бабушка", "промокает"};
    private static final String[] STM_M_KEYWORDS = {"спорт" , "спортивных", "спортивная", "дюсш", "компании", "молодежь", "походы", "пьянки", "спортсмены", "туристы", "мусор", "пугают", "стращают"};
    private static final String[] MIGRATIONS_SERVICE_KEYWORDS = {"узбеки", "узбек", "казахи", "казах", "цыгане", "цыган", "татары", "татарин", "туркмены", "киргизы", "таджики", "туркмен", "киргиз", "таджик", "кавказ", "азиат", "азия", "снг", "ближнего", "ближний"};
    private static final String[] TRANSPORT_M_KEYWORDS = {"газель", "автобус", "троллейбус", "трамвай", "водитель" , "кондуктор", "контроллер", "водила", "обгон", "пассажир", };
    private static final String[] PFR_KEYWORDS = {"пенсии", "пенсия", "пенсионное", "накопление", "деньги", "пропали"};
    private static final String[] HEALTH_M_KEYWORDS = {"больница", "поликлиника", "врач", "медсестра", "медбрат", "палата", "медицинских", "антисанитария", "болезни", "болезнь"};
    private static final String[] CULTURE_M_KEYWORDS = {"тупые" , "быдло" , "школьники", "птушники", "мат", "сигареты", "алкоголь", "шумят", "матерятся", "шум", "мат"};
    private static final String[] EDUCATION_M_KEYWORDS = {"учитель", "преподаватель", "учит", "домашнее", "задание", "задает", "учит", "детей", "кричит", "орет"};
    private static final String[] SANPED_KEYWORDS = {"тараканы", "клопы", "кошки", "собаки", "таракан", "клоп", "кошка", "собака", "дерьмо", "экскременты", "говно", "помет", "фекалии", "вонь", "грязь"};

    private static final GOVERMANT_STRUCTURE[] structures = {
            GOVERMANT_STRUCTURE.MVD, GOVERMANT_STRUCTURE.GIBDD, GOVERMANT_STRUCTURE.FSB, GOVERMANT_STRUCTURE.PROKURATURA, GOVERMANT_STRUCTURE.ROAD_SERVICE, GOVERMANT_STRUCTURE.ZHKH, GOVERMANT_STRUCTURE.TRANSPORT_M,
            GOVERMANT_STRUCTURE.PFR, GOVERMANT_STRUCTURE.HEALTH_M, GOVERMANT_STRUCTURE.CULTURE_M, GOVERMANT_STRUCTURE.EDUCATION_M, GOVERMANT_STRUCTURE.SANPED, GOVERMANT_STRUCTURE.STM_M, GOVERMANT_STRUCTURE.MIGRATIONS_SERVICE
    };

    static {
        keywords = new HashMap<>();

        keywords.put(GOVERMANT_STRUCTURE.MVD, MVD_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.GIBDD, GIBDD_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.FSB, FSB_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.PROKURATURA, PROKURATURA_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.ROAD_SERVICE, ROAD_SERVICE_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.ZHKH, ZHKH_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.TRANSPORT_M, TRANSPORT_M_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.PFR, PFR_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.HEALTH_M, HEALTH_M_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.CULTURE_M, CULTURE_M_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.EDUCATION_M, EDUCATION_M_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.SANPED, SANPED_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.STM_M, STM_M_KEYWORDS);
        keywords.put(GOVERMANT_STRUCTURE.MIGRATIONS_SERVICE, MIGRATIONS_SERVICE_KEYWORDS);
    }

    private CategoryClassifier() {}

    public static GOVERMANT_STRUCTURE classify(String bodyText, String title) {
        String titleWords[] = title.split(" ");
        String bodyWords[] = title.split(" ");

        Map<GOVERMANT_STRUCTURE, Integer> matches = new HashMap<>();

        for (GOVERMANT_STRUCTURE structure : structures) {
            matches.put(structure, 0);
        }

        for(GOVERMANT_STRUCTURE structure : structures) {
            String[] keys = keywords.get(structure);

            for(String word : keys) {
                boolean containsTitle = Arrays.stream(titleWords).anyMatch(word::equals);
                boolean containsBody = Arrays.stream(bodyWords).anyMatch(word::equals);

                if(containsBody) {
                    matches.put(structure, matches.get(structure) + 1);
                }

                if(containsTitle) {
                    matches.put(structure, matches.get(structure) + 1);
                }
            }
        }

        GOVERMANT_STRUCTURE result = null;
        int lastMax = 0;

        for (GOVERMANT_STRUCTURE structure : structures) {
            int val = matches.get(structure);
            if(val > lastMax) {
                lastMax = val;
                result = structure;
            }
        }

        return result;
    }
}
