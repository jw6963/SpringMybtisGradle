package data.service;

import data.dto.SawonDto;
import data.mapper.SawonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SawonService {
    @Autowired
    SawonMapper sawonMapper;
    public void insertSawon(SawonDto sawonDto) {
        sawonMapper.insertSawon(sawonDto);
    }
    public List<SawonDto> getSelectAllSawon() {
        return sawonMapper.getSelectAllSawon();
    }
    public SawonDto getSawon(int num) {
        return sawonMapper.getSawon(num);
    }
    public void deleteSawon(int num) {
        sawonMapper.deleteSawon(num);
    }
    public void updateSawon(SawonDto sawonDto) {
        sawonMapper.updateSawon(sawonDto);
    }
}
