package conan.weiax.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ExcelWeb {
    @RequestMapping("/weiaxC/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String downloadPath = request.getParameter("path");
        List<List<String>> list = ExcelUtils.getList();
        ExcelUtils.exportExcel("/home/hongww/test.xls",list,response);
    }
}
