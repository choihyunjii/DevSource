package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Branch

val branch1 = Branch(
    branchId = 1,
    name = "Branch 1",
    userId = user1,
    projectId = project1,
    push = 10,
    pull = 5,
    crash = 0,
    isMainBranch = 1
)

val branch2 = Branch(
    branchId = 2,
    name = "Branch 2",
    userId = user2,
    projectId = project2,
    push = 8,
    pull = 3,
    crash = 1,
    isMainBranch = 0
)