package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.Branch


var branch1 = Branch(
    branchId = 1,
    userId = "user1",
    projectId = 1,
    push = 1,
    pull = 0,
    crash = 0,
    isMainBranch = 0
)

var branch2 = Branch(
    branchId = 2,
    userId = "user3",
    projectId = 1,
    push = 0,
    pull = 0,
    crash = 1,
    isMainBranch = 0
)

var branch3 = Branch(
    branchId = 3,
    userId = null,
    projectId = 1,
    push = 0,
    pull = 0,
    crash = 0,
    isMainBranch = 1
)

var branchList = mutableListOf(branch1, branch2, branch3)