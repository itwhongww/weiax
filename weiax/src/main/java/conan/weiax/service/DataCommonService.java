package conan.weiax.service;

import com.alibaba.fastjson.JSONObject;
import conan.weiax.config.annotation.Read;
import conan.weiax.config.annotation.Write;
import conan.weiax.dao.DataCommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * describe:
 *
 * @author hongww
 * @date 2018/03/19
 */
@Service
public class DataCommonService {
    @Autowired
    private DataCommonMapper dataCommonMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Read
    public JSONObject findData(String dbResult,String tableName,String dbWhere){
        JSONObject json = new JSONObject();
        json.put("dbResult",dbResult);
        json.put("tableName",tableName);
        json.put("dbWhere",dbWhere);
        return dataCommonMapper.findCommonData(json);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Read
    public List<JSONObject> findDatas(String dbResult, String tableName, String dbWhere){
        JSONObject json = new JSONObject();
        json.put("dbResult",dbResult);
        json.put("tableName",tableName);
        json.put("dbWhere",dbWhere);
        return dataCommonMapper.findCommonDatas(json);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Write
    public void insertData(String tableName, String dbWhere, String dbResult){
        JSONObject json = new JSONObject();
        json.put("tableName",tableName);
        json.put("dbWhere",dbWhere);
        json.put("dbResult",dbResult);
        dataCommonMapper.insertData(json);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Write
    public void updateData(String tableName, String dbResult, String dbWhere){
        JSONObject json = new JSONObject();
        json.put("tableName",tableName);
        json.put("dbWhere",dbWhere);
        json.put("dbResult",dbResult);
        dataCommonMapper.updateData(json);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Write
    public void deleteData(String tableName, String dbWhere){
        JSONObject json = new JSONObject();
        json.put("tableName",tableName);
        json.put("dbWhere",dbWhere);
        dataCommonMapper.deleteData(json);
    }

}
