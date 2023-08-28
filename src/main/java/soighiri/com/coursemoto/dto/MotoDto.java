package soighiri.com.coursemoto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoDto {
    private Long idMoto;
    private String marqueMoto;
    private String versionMoto;
    private String puissanceMoto;
    private String modeleMoto;


}
