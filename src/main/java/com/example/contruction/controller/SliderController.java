package com.example.contruction.controller;

import com.example.contruction.dto.SliderDTO;
import com.example.contruction.service.SliderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slider")
public class SliderController {

    private final SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }

    @GetMapping("/{language}")
    public List<SliderDTO> getSliders(@PathVariable String language) {
        return sliderService.getSlidersByLanguage(language);
    }

    @PostMapping
    public SliderDTO saveOrUpdateSlider(@RequestBody SliderDTO sliderDTO) {
        return sliderService.saveOrUpdateSlider(sliderDTO);
    }

    @PutMapping("/{id}")
    public SliderDTO updateSlider(@PathVariable Long id, @RequestBody SliderDTO sliderDTO) {
        sliderDTO.setId(id);  // ID set et
        return sliderService.saveOrUpdateSlider(sliderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSlider(@PathVariable Long id) {
        sliderService.deleteSlider(id);
    }

}