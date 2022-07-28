package com.qf.controller;

import com.qf.service.*;
import com.qf.util.ResponseUtil;
import com.qf.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 哇哈哈
 * @ClassName AdminController
 * @description: TODO
 * @datetime 2022年 07月 22日 11:00
 * @version: 1.0
 */
@RequestMapping("/admin/dashboard")
@RestController
@CrossOrigin
public class AdminController {
          public static final Integer STATIS_DAYS_RUNG = 30;
          @Autowired(required = false)
          private DtsUserService dtsUserService;
          @Autowired(required = false)
          private DtsOrderService dtsOrderService;
          @Autowired(required = false)
          private DtsGoodsService dtsGoodsService;
          @Autowired(required = false)
          private DtsGoodsProductService dtsGoodsProductService;
          @GetMapping()
          public Object dashboard(){
                    Map<String, Object> map = new HashMap<>();
                    Integer goodsTotal = dtsGoodsService.selectCount();
                    Integer userTotal = dtsUserService.selectCount();
                    Integer productTotal = dtsGoodsProductService.selectCount();
                    Integer orderTotal = dtsOrderService.selectCount();
                    /**
                     * {
                     *     "errno":0,
                     *     "data":{
                     *         "goodsTotal":426,
                     *         "userTotal":197,
                     *         "productTotal":8939,
                     *         "orderTotal":107
                     *     },
                     *     "errmsg":"成功"
                     * }
                     */
                    map.put("goodsTotal",goodsTotal);
                    map.put("userTotal",userTotal);
                    map.put("productTotal",productTotal);
                    map.put("orderTotal",orderTotal);
                    return ResponseUtil.ok(map);
          }
          @Autowired
          private CategorySellVoService categorySellVoService;
//          饼状图
          @GetMapping("/chart")
          public Object chart(){
                    CategorySellVo categorySellVo = new CategorySellVo();
                    List<CategorySellAmts> categorySellAmtsList = categorySellVoService.findCategorySellV0();
                    categorySellVo.setCategorySellData(categorySellAmtsList);
                    categorySellVo.setCategoryNames( categorySellAmtsList.stream()
                            .map(CategorySellAmts::getName)
                            .collect(Collectors.toList())
                            .toArray(new String[categorySellAmtsList.size()]));
                    /**
                     * "orderAmts":{
                     *             "dayData":[
                     *                 "2119-12-01","2119-12-02","2119-12-03","2119-12-04",
                     *                 "2119-12-05","2119-12-06","2119-12-07","2119-12-08",
                     *                 "2119-12-11","2119-12-13","2119-12-15","2119-12-16",
                     *                 "2119-12-17","2119-12-19","2119-12-20","2119-12-23",
                     *                 "2119-12-25","2119-12-27","2119-12-28","2119-12-29",
                     *                 "2119-12-30"
                     *             ],
                     *             "orderAmtData":[
                     *                 6.88,138.79,277.65,65.64,182.00,61.00,
                     *                 289.01,375.76,29.50,247.50,145.50,
                     *                 31.18,399.44,16.99,150.01,218.65,255.77,
                     *                 126.59,275.25,97.74,14.63
                     *             ],
                     *             "orderCntData":[
                     *                 1,2,4,1,3,1,2,3,1,1,16,3,9,2,1,2,2,2,2,2,2
                     *             ]
                     *         },
                     */
                    List<DayStatis> dayStatisList = dtsOrderService.findOrderDayStatisList();
                    OrderAmtsVo orderAmtsVo = new OrderAmtsVo();
//                日期集合
                    orderAmtsVo.setDayData(dayStatisList.stream().map(DayStatis::getDayStr).collect(Collectors.toList()).toArray(new String[dayStatisList.size()]));
//                    每日订单数量
                    orderAmtsVo.setOrderCntData(dayStatisList.stream().map(DayStatis::getCnts).collect(Collectors.toList()).toArray(new Integer[dayStatisList.size()]));
//                  每日订单金额
                    orderAmtsVo.setOrderAmtData(dayStatisList.stream().map(DayStatis::getAmts).collect(Collectors.toList()).toArray(new BigDecimal[dayStatisList.size()]));
                    /**
                     * "userOrderCnt":{
                     *             "dayData":[
                     *                 "2119-12-01","2119-12-02","2119-12-03","2119-12-04",
                     *                 "2119-12-05","2119-12-06","2119-12-07","2119-12-08",
                     *                 "2119-12-09","2119-12-10","2119-12-11","2119-12-12",
                     *                 "2119-12-13","2119-12-14","2119-12-15","2119-12-16",
                     *                 "2119-12-17","2119-12-18","2119-12-19","2119-12-20",
                     *                 "2119-12-21","2119-12-22","2119-12-23","2119-12-24",
                     *                 "2119-12-25","2119-12-26","2119-12-27","2119-12-28",
                     *                 "2119-12-29","2119-12-30"
                     *             ],
                     *             "userCnt":[
                     *                 5,9,13,7,6,2,1,3,17,6,2,9,8,17,23,8,
                     *                 4,8,8,14,3,2,1,1,2,1,2,7,3,5
                     *             ],
                     *             "orderCnt":[
                     *                 1,2,4,1,3,1,2,3,0,0,1,0,1,0,16,3,9,
                     *                 0,2,1,0,0,2,0,2,0,2,2,2,2
                     *             ]
                     *         }
                     *     },
                     *     "errmsg":"成功"
                     */
                    List<DayStatis> dayNumList =  dtsUserService.findOrderDayNumList(STATIS_DAYS_RUNG);
                    UserOrderCntVo userOrderCntVo = new UserOrderCntVo();
                    String[] megerDay = megerDay(dayStatisList, dayNumList);
                    userOrderCntVo.setDayData(megerDay);
                    userOrderCntVo.setOrderCnt(findStatisByDayData(megerDay,dayStatisList));
                    userOrderCntVo.setUserCnt(findStatisByDayData(megerDay,dayNumList));
                    Map<String,Object> map = new HashMap<>();

                    map.put("categorySell",categorySellVo);
                    map.put("orderAmts",orderAmtsVo);
                    map.put("userOrderCnt",userOrderCntVo);


                    return ResponseUtil.ok(map);

          }
//          订单日期于用户日期合并
//          参数一是订单日期 参数二是用户日期
          public String[] megerDay(List<DayStatis> dayStatisList,List<DayStatis> userDayNumList){
                    Set<String> daySet = new HashSet<>();
                    dayStatisList.stream().forEach(dayStatis -> {
                              daySet.add(dayStatis.getDayStr());
                    });
                    userDayNumList.stream().forEach(dayStatis -> {
                              daySet.add(dayStatis.getDayStr());
                    });
//                    Collections.sort(new ArrayList<String>(daySet),(o1,o2) -> {
//                              return Integer.parseInt(o2) - Integer.parseInt(o1);
//                    });
                    List<String> dayList = new ArrayList<>(daySet);
                    Collections.sort(dayList);
                    return dayList.toArray(new String[0]);
          }
//          根据日期找订单笔数，找不到写0
//          根据日期找用户新增，找不到写0
          public Integer[] findStatisByDayData(String[] dayData,List<DayStatis> dayStatisList){
                    List<Integer> cntsArray = new ArrayList<>(dayData.length);
                    for (String day : dayData) {
//                              标记
                              boolean flag = false;
                              for (DayStatis dayStatis : dayStatisList) {
                                        if (dayStatis.getDayStr().equalsIgnoreCase(day)) {
                                                  cntsArray.add(dayStatis.getCnts());
                                                  flag = true;
                                                  break;
                                        }
                              }
//                              判断
                              if (!flag){
//                                        循环完了还是没有
                                        cntsArray.add(0);
                              }
                    }
                    return cntsArray.toArray(new Integer[dayData.length]);
          }

}
