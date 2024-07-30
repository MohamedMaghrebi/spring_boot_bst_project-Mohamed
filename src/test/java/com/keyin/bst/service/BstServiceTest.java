//BstServiceTest
package com.keyin.bst.service;

import com.keyin.bst.model.BinarySearchTree;
import com.keyin.bst.model.TreeEntity;
import com.keyin.bst.model.TreeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BstServiceTest {

    @InjectMocks
    private BstService bstService;

    @Mock
    private TreeRepository treeRepository;

    @Test
    public void testCreateTree() {
        List<Integer> numbers = Arrays.asList(10, 5, 20);
        BinarySearchTree bst = bstService.createTree(numbers);
        assertNotNull(bst);
    }

    @Test
    public void testSaveTree() {
        bstService.saveTree("10,5,20", "{}");
        verify(treeRepository, times(1)).save(any(TreeEntity.class));
    }

    @Test
    public void testGetAllTrees() {
        when(treeRepository.findAll()).thenReturn(Arrays.asList(new TreeEntity()));
        List<TreeEntity> trees = bstService.getAllTrees();
        assertNotNull(trees);
    }
}
