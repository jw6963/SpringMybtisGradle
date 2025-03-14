package data.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("SawonDto")
public class SawonDto {
    private int num;
    private String sawonName;
    private String photo;
    private String gender;
    private String hp;
    private String buseo;
    private String ipsaday;
    private Timestamp writeday;
}
