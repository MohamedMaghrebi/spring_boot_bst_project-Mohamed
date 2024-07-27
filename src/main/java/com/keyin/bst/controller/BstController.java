
package com.keyin.bst.controller;

import com.keyin.bst.model.BinarySearchTree;
import com.keyin.bst.model.TreeEntity;
import com.keyin.bst.service.BstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class BstController {

    @Autowired
    private BstService bstService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enterNumbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestBody String numbers, Model model) {
        List<Integer> numberList = Stream.of(numbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        BinarySearchTree bst = bstService.createTree(numberList);
        String treeJson = convertTreeToJson(bst);  // Convert bst to JSON representation

        bstService.saveTree(numbers, treeJson);
        model.addAttribute("tree", treeJson);
        return "treeView";  // Create this template to show the tree
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        List<TreeEntity> trees = bstService.getAllTrees();
        model.addAttribute("trees", trees);
        return "previousTrees";
    }

    private String convertTreeToJson(BinarySearchTree bst) {
        // Implement this method to convert bst to JSON
        return "";
    }
}