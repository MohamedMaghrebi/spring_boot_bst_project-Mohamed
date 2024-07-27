
package com.keyin.bst.service;

import com.keyin.bst.model.BinarySearchTree;
import com.keyin.bst.model.TreeEntity;
import com.keyin.bst.model.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BstService {

    @Autowired
    private TreeRepository treeRepository;

    public BinarySearchTree createTree(List<Integer> numbers) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int num : numbers) {
            bst.insert(num);
        }
        return bst;
    }

    public void saveTree(String numbers, String treeStructure) {
        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setNumbers(numbers);
        treeEntity.setTreeStructure(treeStructure);
        treeRepository.save(treeEntity);
    }

    public List<TreeEntity> getAllTrees() {
        return treeRepository.findAll();
    }
}