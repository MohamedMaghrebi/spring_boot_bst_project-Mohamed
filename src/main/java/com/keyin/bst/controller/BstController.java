
package com.keyin.bst.controller;

import com.keyin.bst.model.BinarySearchTree;
import com.keyin.bst.model.Node;
import com.keyin.bst.model.TreeEntity;
import com.keyin.bst.service.BstService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BstController {

    @Autowired
    private BstService bstService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enterNumbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers, Model model) {
        List<Integer> numberList = Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        BinarySearchTree bst = bstService.createTree(numberList);
        String treeJson = convertTreeToJson(bst.getRoot());  // Convert bst to JSON representation

        bstService.saveTree(numbers, treeJson);
        model.addAttribute("tree", treeJson);
        return "treeView";
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        List<TreeEntity> trees = bstService.getAllTrees();
        model.addAttribute("trees", trees);
        return "previousTrees";
    }

    private String convertTreeToJson(Node root) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}