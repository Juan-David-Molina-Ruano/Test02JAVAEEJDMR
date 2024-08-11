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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.Test02JAVAEEJDMR.Models.ProductoJDMR;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IProductoJDMRService;

@Controller
@RequestMapping("/productos")
public class ProductoJDMRController {
    @Autowired
    private IProductoJDMRService productoJDMRService;

    @GetMapping("/index")
    public String index(Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        Page<ProductoJDMR> productos = productoJDMRService.buscarTodosPaginados(pageable);
        model.addAttribute("productos", productos);

        if (productos.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, productos.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "productos/index";
    }

    @GetMapping("/create")
    public String create(ProductoJDMR productoJDMR) {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(ProductoJDMR productoJDMR, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(productoJDMR);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "productos/create";
        }

        productoJDMRService.crearOEditar(productoJDMR);
        attributes.addFlashAttribute("msg", "Producto creado correctamente");
        return "redirect:/productos/index";
    }
}
