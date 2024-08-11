package com.test.Test02JAVAEEJDMR.Controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.Test02JAVAEEJDMR.Models.DetalleOrdenJDMR;
import com.test.Test02JAVAEEJDMR.Models.OrdenJDMR;
import com.test.Test02JAVAEEJDMR.Models.ProductoJDMR;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IDetalleOrdenJDMRService;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IOrdenJDMRService;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IProductoJDMRService;

@Controller
@RequestMapping("/detallesorden")
public class DetalleOrdenController {

    @Autowired
    private IDetalleOrdenJDMRService detalleOrdenService;

    @Autowired
    private IProductoJDMRService productoJDMRService;

    @Autowired
    private IOrdenJDMRService ordenJDMRService;

    @GetMapping("/index")
    public String index(Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        Page<DetalleOrdenJDMR> detalles = detalleOrdenService.buscarTodosPaginados(pageable);
        model.addAttribute("detalles", detalles);

        if (detalles.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, detalles.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "detallesorden/index";
    }

    @GetMapping("/create")
    public String create(DetalleOrdenJDMR detalleOrdenJDMR, Model model) {
        model.addAttribute("ordenes", ordenJDMRService.obtenerTodos());
        model.addAttribute("productos", productoJDMRService.obtenerTodos());

        return "detallesorden/create";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam Long producto_id,
            @RequestParam Long orden_id,
            @RequestParam Integer cantidadJDMR,
            @RequestParam BigDecimal precioJDMR,
            RedirectAttributes attributes) {
        // Verifica si el producto y la orden existen (esto depende de tu implementación
        // de servicios)
        ProductoJDMR producto = productoJDMRService.buscarPorId(producto_id).orElse(null);
        OrdenJDMR orden = ordenJDMRService.buscarPorId(orden_id).orElse(null);

        if (producto != null && orden != null) {
            // Crea un nuevo objeto DetalleOrdenJDMR
            DetalleOrdenJDMR detalleOrdenJDMR = new DetalleOrdenJDMR();
            detalleOrdenJDMR.setProductoJDMR(producto);
            detalleOrdenJDMR.setOrdenJDMR(orden);
            detalleOrdenJDMR.setCantidadJDMR(cantidadJDMR);
            detalleOrdenJDMR.setPrecioJDMR(precioJDMR);

            // Guarda el objeto en la base de datos (esto depende de tu implementación de
            // servicios)
            detalleOrdenService.crearOEditar(detalleOrdenJDMR);

            // Añade un mensaje de éxito para mostrar en la vista
            attributes.addFlashAttribute("msg", "Detalle de orden guardado correctamente");
        } else {
            // Añade un mensaje de error en caso de que producto o orden no se encuentren
            attributes.addFlashAttribute("error", "Producto o orden no encontrados");
        }

        // Redirige a la vista de la lista de detalles de orden
        return "redirect:/detallesorden/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<DetalleOrdenJDMR> detalleOrdenJDMR = detalleOrdenService.buscarPorId(id);
        if (detalleOrdenJDMR.isPresent()) {
            model.addAttribute("detalleOrdenJDMR", detalleOrdenJDMR.get());
        } else {
            // Manejar el caso en el que no se encuentre el detalle de la orden
            return "redirect:/detallesorden/index";
        }
        model.addAttribute("ordenes", ordenJDMRService.obtenerTodos());
        model.addAttribute("productos", productoJDMRService.obtenerTodos());
        return "detallesorden/edit";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam Long id,
            @RequestParam Long producto_id,
            @RequestParam long orden_id,
            @RequestParam Integer cantidadJDMR,
            @RequestParam BigDecimal precioJDMR,
            RedirectAttributes attributes) {

        ProductoJDMR productoJDMR = productoJDMRService.buscarPorId(producto_id).orElse(null);
        OrdenJDMR ordenJDMR = ordenJDMRService.buscarPorId(orden_id).orElse(null);

        if (productoJDMR != null && ordenJDMR != null) {
            Optional<DetalleOrdenJDMR> optdetalleOrden = detalleOrdenService.buscarPorId(id);

            if (optdetalleOrden.isPresent()) {
                DetalleOrdenJDMR detalleOrdenJDMR = optdetalleOrden.get();

                detalleOrdenJDMR.setProductoJDMR(productoJDMR);
                detalleOrdenJDMR.setOrdenJDMR(ordenJDMR);
                detalleOrdenJDMR.setCantidadJDMR(cantidadJDMR);
                detalleOrdenJDMR.setPrecioJDMR(precioJDMR);

                // Guardar los cambios
                detalleOrdenService.crearOEditar(detalleOrdenJDMR);
                attributes.addFlashAttribute("msg", "Pedido modificado correctamente");
            } else {
                attributes.addFlashAttribute("error", "No se encontró el pedido con ID: " + id);
            }
        } else {
            attributes.addFlashAttribute("error", "Orden o producto no encontrados");
        }

        return "redirect:/detallesorden/index"; // Redirige a la página de asignaciones
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        Optional<DetalleOrdenJDMR> detalleOrdenJDMR = detalleOrdenService.buscarPorId(id);
        if (detalleOrdenJDMR.isPresent()) {
            model.addAttribute("detalleOrdenJDMR", detalleOrdenJDMR.get());
            // Añadir las listas de órdenes y productos al modelo
            model.addAttribute("ordenes", ordenJDMRService.obtenerTodos());
            model.addAttribute("productos", productoJDMRService.obtenerTodos());
        } else {
            model.addAttribute("error", "Detalle de orden no encontrado");
            return "redirect:/detallesorden/index";
        }
        return "/detallesorden/details";
    }
    
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model) {
        Optional<DetalleOrdenJDMR> detalleOrdenJDMR = detalleOrdenService.buscarPorId(id);
        if (detalleOrdenJDMR.isPresent()) {
            model.addAttribute("ordenes", ordenJDMRService.obtenerTodos());
            model.addAttribute("productos", productoJDMRService.obtenerTodos());
            model.addAttribute("detalleOrdenJDMR", detalleOrdenJDMR.get());
            return "/detallesorden/delete";
        } else {
            model.addAttribute("error", "Detalle de orden no encontrado");
            return "redirect:/detallesorden/index";
        }
    }
    
    

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes attributes){
        detalleOrdenService.eliminarPorId(id);
        attributes.addFlashAttribute("msg", "Pedido eliminado correctamente");
        return "redirect:/detallesorden/index";
    }    
}
