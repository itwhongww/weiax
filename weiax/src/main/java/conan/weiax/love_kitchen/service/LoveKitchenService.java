package conan.weiax.love_kitchen.service;

import com.alibaba.fastjson.JSONObject;
import conan.weiax.config.annotation.Read;
import conan.weiax.dao.DataCommonMapper;
import conan.weiax.exception.GlobalException;
import conan.weiax.love_kitchen.LoveKitchenConstains;
import conan.weiax.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoveKitchenService {
    @Autowired
    private DataCommonMapper dataCommonMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Read
    public List<JSONObject> showAllMenu(JSONObject json) {
        String username = json.getString("username");

        JSONObject daoJson = new JSONObject();
        daoJson.put("tableName", LoveKitchenConstains.TABLENAME_LOVE_KITCHEN_MENU);
        daoJson.put("dbResult", "id,menuName,description,note,menuOrder,status,createUser,createTime");
        boolean isAdmin = SecurityUtils.isAuthority(username);
        if (isAdmin) {
            daoJson.put("dbWhere", "1=1");
        } else {
            daoJson.put("dbWhere", "createUser = '" + username + "'");
        }
        List<JSONObject> returnList = dataCommonMapper.findCommonDatas(daoJson);
        return returnList;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Read
    public JSONObject showMenuDetail(JSONObject json) {
        String id = json.getString("id");
        String username = json.getString("username");
        JSONObject daoJson = new JSONObject();
        daoJson.put("tableName", LoveKitchenConstains.TABLENAME_LOVE_KITCHEN_MENU);
        daoJson.put("dbResult", "id,menuName,description,note,menuOrder,status,createUser,createTime");
        daoJson.put("dbWhere", "id = '" + id + "'");

        JSONObject menuJson = dataCommonMapper.findCommonData(daoJson);
        boolean isAdmin = SecurityUtils.isAuthority(username);
        boolean flag = false;
        if (isAdmin) {
            flag = true;
        } else {
            String createUser = menuJson.getString("createUser");
            if (username != null && username.equals(createUser)) {
                flag = true;
            }
        }
        if (flag) {
            daoJson.clear();
            daoJson.put("tableName", LoveKitchenConstains.TABLENAME_LOVE_KITCHEN_IMAGE);
            daoJson.put("dbResult", "id,menuId,imgUrl,imgOrder");
            daoJson.put("dbWhere", "menuId = '" + id + "'");
            List<JSONObject> imgList = dataCommonMapper.findCommonDatas(daoJson);
            menuJson.put("imgList", imgList);
        } else {
            throw new GlobalException("非法进入，IP定位中...");
        }

        return menuJson;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Read
    public List<JSONObject> showMenuClient(JSONObject json) {
        JSONObject daoJson = new JSONObject();
        daoJson.put("tableName", LoveKitchenConstains.TABLENAME_LOVE_KITCHEN_MENU + " a," + LoveKitchenConstains.TABLENAME_LOVE_KITCHEN_IMAGE + " b");
        daoJson.put("dbResult", "a.id,a.menuName,a.description,a.note,a.menuOrder,a.status,a.createUser,a.createTime,b.imgUrl ");

        daoJson.put("dbWhere", "a.id=b.menuId and b.id=(select d.id from LOVE_KITCHEN_MENU c ,LOVE_KITCHEN_IMAGE d where c.id =d.menuId order by imgOrder limit 0,1)");
        List<JSONObject> menuList = dataCommonMapper.findCommonDatas(daoJson);

        return menuList;
    }
}
