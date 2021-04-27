package com.lxd.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxd.pojo.Type;
import com.lxd.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author : Lxd
 * @date : 22:05 2021/4/5
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    //分页查询列表
    @GetMapping("/types")
    public String list(Model model , @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照字段排序 倒序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Type> typeList = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //返回新增分类页面
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //新增分类
    @PostMapping("/types")
    public String post(Type type , RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1!=null){
            attributes.addAttribute("message","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.saveType(type);
        if (t==0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    //跳转修改分类页面
    @GetMapping("/types/{id}/input")
    public String update(@PathVariable Long id , Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //修改分类
    @PostMapping("/types/{id}")
    public String edit(@Validated Type type , RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            attributes.addAttribute("message","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.updateType(type);
        if (t == 0){
            attributes.addFlashAttribute("message","编辑失败");
        }else{
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/types";
    }

    //删除分类
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
