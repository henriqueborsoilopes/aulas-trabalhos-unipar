package br.unipar.husistema.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Util {
    
    /**
     * Formato yyyy-mm-dd HH:mm:ss
     * @param data
     * @return string
     */
    public static String getAnoMesDiaHora(LocalDateTime data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(data);
    }
    
    /**
     * Formato yyyy-mm-dd
     * @param data
     * @return string
     */
    public static String getAnoMesDia(LocalDateTime data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(data);
    }
}
