package com.test.Test02JAVAEEJDMR.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.Test02JAVAEEJDMR.Models.OrdenJDMR;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IOrdenJDMRService;

@Controller
@RequestMapping("/ordenes")
public class OrdenJDMRController {
    @Autowired
    private IOrdenJDMRService ordenJDMRService;

    @GetMapping("/index")
    public String index(Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        Page<OrdenJDMR> ordenes = ordenJDMRService.buscarTodosPaginados(pageable);
        model.addAttribute("ordenes", ordenes);

        if (ordenes.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, ordenes.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "ordenes/index";
    }

    @GetMapping("/create")
    public String create(OrdenJDMR ordenJDMR) {
        return "ordenes/create";
    }

    @PostMapping("/save")
    public String save(OrdenJDMR ordenJDMR, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(ordenJDMR);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "ordenes/create";
        }

        ordenJDMRService.crearOEditar(ordenJDMR);
        attributes.addFlashAttribute("msg", "Orden creada correctamente");
        return "redirect:/ordenes/index";
    }

     @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        OrdenJDMR ordenJDMR = ordenJDMRService.buscarPorId(id).get();
        model.addAttribute("ordenJDMR", ordenJDMR);
        return "/ordenes/edit";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        Optional<OrdenJDMR> ordeOptional = ordenJDMRService.buscarPorId(id);
        if (ordeOptional.isPresent()) {
            OrdenJDMR ordenJDMR = ordeOptional.get();
            model.addAttribute("ordenJDMR", ordenJDMR);
            return "/ordenes/details"; // Asegúrate de que esta ruta coincide con la estructura de carpetas
        } else {
            // Manejo de error si el producto no existe
            return "redirect:/ordenes/index"; // Redirige a una página de error o lista
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model) {
        System.out.println("Remove method called with ID: " + id);
        Optional<OrdenJDMR> orOptional = ordenJDMRService.buscarPorId(id);
        if (orOptional.isPresent()) {
            OrdenJDMR ordenJDMR = orOptional.get();
            model.addAttribute("ordenJDMR", ordenJDMR);
            return "/ordenes/delete";
        } else {
            return "redirect:/ordenes/index";
        }
    }

    @PostMapping("/delete")
    public String delete(OrdenJDMR ordenJDMR, RedirectAttributes attributes) {
        ordenJDMRService.eliminarPorId(ordenJDMR.getId());
        attributes.addFlashAttribute("msg", "Orden eliminada correctamente");
        return "redirect:/ordenes/index";
    }
}
