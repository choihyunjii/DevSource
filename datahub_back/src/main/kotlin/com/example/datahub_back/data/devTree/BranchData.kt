package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Branch


val branch1 = Branch(
    branchId = 1,
    userId = user1,
    projectId = project1,
    push = 1,
    pull = 0,
    crash = 0,
    isMainBranch = 0
)

val branch2 = Branch(
    branchId = 2,
    userId = user3,
    projectId = project1,
    push = 0,
    pull = 0,
    crash = 1,
    isMainBranch = 0
)

val branch3 = Branch(
    branchId = 3,
    userId = null,
    projectId = project1,
    push = 0,
    pull = 0,
    crash = 0,
    isMainBranch = 1
)

val branchList = mutableListOf(branch1, branch2, branch3)