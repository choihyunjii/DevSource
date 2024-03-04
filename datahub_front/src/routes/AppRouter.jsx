// import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
// import MainPage from '../authentication/components/page/MainPage'
// import CreateProjectPage from '../project/components/page/CreateProjectPage'
// import DataBaseShowCasePage from '../project/components/page/DataBaseShowCasePage'
// import ProjectShowCasePage from '../project/components/page/ProjectShowCasePage'
// import TemplatePage from '../template/components/page/TemplatePage'
// import AuthProvider, { useAuth } from './security/AuthContext'
// import ErrorPage from "./ErrorPage";
//
// function AuthenticatedRoute({children}) {
//     const authContext = useAuth()
//
//     if(authContext.isAuthenticated)
//         return children
//
//     return <Navigate to="/" />
// }
//
// export default function AppRouter() {
//     return (
//         <div className="AppRouter">
//             <AuthProvider>
//                 {/* 매핑 */}
//                 <BrowserRouter>
//                     <Routes>
//                         <Route path='/main' element={<MainPage />}/>
//                         {/*<Route path='/login' element={<LoginPage />}/>*/}
//                         <Route path='/createProject' element={
//                             <AuthenticatedRoute>
//                                 <CreateProjectPage />
//                             </AuthenticatedRoute>
//                         }/>
//                         <Route path='/dataBases' element={
//                             <AuthenticatedRoute>
//                                 <DataBaseShowCasePage />
//                             </AuthenticatedRoute>
//                         }/>
//                         <Route path='/projects' element={
//                             <AuthenticatedRoute>
//                                 <ProjectShowCasePage />
//                             </AuthenticatedRoute>
//                         }/>
//                         <Route path='/template' element={
//                             <AuthenticatedRoute>
//                                 <TemplatePage />
//                             </AuthenticatedRoute>
//                         }/>
//                         <Route path='*' element={<ErrorPage />}/>
//                     </Routes>
//                 </BrowserRouter>
//             </AuthProvider>
//         </div>
//     )
// }