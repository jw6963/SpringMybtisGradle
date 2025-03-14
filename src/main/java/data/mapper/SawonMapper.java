package data.mapper;

import data.dto.SawonDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SawonMapper {
    public void insertSawon(SawonDto sawon);
    public List<SawonDto> getSelectAllSawon();
    public SawonDto getSawon(int num);
    public void deleteSawon(int num);
    public void updateSawon(SawonDto sawon);
}
