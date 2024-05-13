{
	"info": {
		"_postman_id": "f8d36c12-58ae-4352-81c3-e559e17f8174",
		"name": "Ecommerce",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "28743693"
	},
	"item": [
		{
			"name": "Permit-all",
			"item": [
				{
					"name": "Sign-up",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"userName\":\"tung_1905\",\r\n    \"password\":\"tung@1234\",\r\n    \"confirmPassword\":\"tung@1234\",\r\n    \"fullName\": \"nguyen phuong thi\",\r\n    \"email\":\"tung1234@gmail.com\",\r\n    \"phone\":\"080222222\",\r\n    \"address\": \"liên chiểu, đà nẵng\",\r\n    \"roles\": [\"ROLE_USER\"]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8088/api.myservice.com/v1/auth/sign-up",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8088",
							"path": [
								"api.myservice.com",
								"v1",
								"auth",
								"sign-up"
							]
						}
					},
					"response": []
				},
				{
					"name": "Sign-in",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"var resp = JSON.parse(responseBody);\r",
									"pm.environment.set(\"token\", resp.accessToken);\r",
									"pm.environment.set(\"userId\", resp.id);"
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n\"userName\":\"tung_1721\",\r\n\"password\":\"tung@1234\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8088/api.myservice.com/v1/auth/sign-in",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8088",
							"path": [
								"api.myservice.com",
								"v1",
								"auth",
								"sign-in"
							]
						}
					},
					"response": []
				},
				{
					"name": "search-by-name-or-description",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8088/api.myservice.com/v1/products/search?productName=sản ẩm a",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8088",
							"path": [
								"api.myservice.com",
								"v1",
								"products",
								"search"
							],
							"query": [
								{
									"key": "productName",
									"value": "sản ẩm a"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "search-all-products-page-sorted",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8088/api.myservice.com/v1/products?page=1&size=5&sort=productName,desc",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8088",
							"path": [
								"api.myservice.com",
								"v1",
								"products"
							],
							"query": [
								{
									"key": "page",
									"value": "1"
								},
								{
									"key": "size",
									"value": "5"
								},
								{
									"key": "sort",
									"value": "productName,desc"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Admin",
			"item": [
				{
					"name": "User",
					"item": [
						{
							"name": "add-new-user",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/users?page=1&size=5&sort=userName,desc",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"users"
									],
									"query": [
										{
											"key": "page",
											"value": "1"
										},
										{
											"key": "size",
											"value": "5"
										},
										{
											"key": "sort",
											"value": "userName,desc"
										}
									]
								}
							},
							"response": []
						},
						{
							"name": "add-role",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/users/11/role/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"users",
										"11",
										"role",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "delete-role",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "DELETE",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/users/11/role/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"users",
										"11",
										"role",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "switch-user-status",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/users/11",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"users",
										"11"
									]
								}
							},
							"response": []
						},
						{
							"name": "get-role-list",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/roles",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"roles"
									]
								}
							},
							"response": []
						},
						{
							"name": "search-users-by-userName",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [
									{
										"key": "search",
										"value": "thi",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/users/search?search=abc&page=1&size=5&sort=userName,desc",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"users",
										"search"
									],
									"query": [
										{
											"key": "search",
											"value": "abc"
										},
										{
											"key": "page",
											"value": "1"
										},
										{
											"key": "size",
											"value": "5"
										},
										{
											"key": "sort",
											"value": "userName,desc"
										}
									]
								}
							},
							"response": []
						},
						{
							"name": "search-products-by-productName",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [
									{
										"key": "search",
										"value": "thi",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/products?page=1&size=5&sort=productName,desc",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"products"
									],
									"query": [
										{
											"key": "page",
											"value": "1"
										},
										{
											"key": "size",
											"value": "5"
										},
										{
											"key": "sort",
											"value": "productName,desc"
										}
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Products",
					"item": [
						{
							"name": "add-new-product",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "POST",
								"header": [
									{
										"key": "search",
										"value": "thi",
										"disabled": true
									}
								],
								"body": {
									"mode": "formdata",
									"formdata": [
										{
											"key": "file",
											"contentType": "multipart/form-data",
											"type": "file",
											"src": "/G:/GG Driver/02. Academy/Rikei/Full-stack course/02. Projects/06.Final project/Module_2/e-Website/public/img/cart/printed-dress.jpg"
										},
										{
											"key": "request",
											"value": "{\n    \"productName\":\"Sản phẩm F\",\n    \"description\": \"mô tả F \",\n    \"unitPrice\" : 1100,\n    \"stockQuantity\": 10,\n    \"categoryId\" : 1\n}",
											"contentType": "application/json",
											"type": "text"
										}
									]
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/products",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"products"
									]
								}
							},
							"response": []
						},
						{
							"name": "update-product",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "formdata",
									"formdata": [
										{
											"key": "file",
											"contentType": "multipart/form-data",
											"type": "file",
											"src": "/G:/GG Driver/02. Academy/Rikei/Full-stack course/02. Projects/06.Final project/Module_2/e-Website/public/img/cart/faded-short-sleeves-tshirt.jpg"
										},
										{
											"key": "request",
											"value": "{\n    \"productName\":\"Sản phẩm A\",\n    \"description\": \"sản phẩm loại A \",\n    \"unitPrice\" : 13000,\n    \"stockQuantity\": 11,\n    \"categoryId\" : 1\n}",
											"contentType": "application/json",
											"type": "text"
										}
									]
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/products/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"products",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "delete-product",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "DELETE",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/products/3",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"products",
										"3"
									]
								}
							},
							"response": []
						},
						{
							"name": "search-product-by-id",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/products/3",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"products",
										"3"
									]
								}
							},
							"response": []
						},
						{
							"name": "search-all-products-page",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/products?page=1&size=5&sort=productName,desc",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"products"
									],
									"query": [
										{
											"key": "page",
											"value": "1"
										},
										{
											"key": "size",
											"value": "5"
										},
										{
											"key": "sort",
											"value": "productName,desc"
										}
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Categories",
					"item": [
						{
							"name": "add-new-category",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "POST",
								"header": [
									{
										"key": "search",
										"value": "thi",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\r\n    \"categoryName\":\"Danh mục D\",\r\n    \"description\": \"mô tả cho danh mục D \"\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/categories",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"categories"
									]
								}
							},
							"response": []
						},
						{
							"name": "update-category",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n    \"productName\":\"Sản phẩm A\",\r\n    \"description\": \"sản phẩm loại A \",\r\n    \"unitPrice\" : 13000,\r\n    \"stockQuantity\": 11,\r\n\r\n    \"categoryId\" : 1\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/products/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"products",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "delete-category",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "DELETE",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/categories/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"categories",
										"1"
									]
								}
							},
							"response": []
						},
						{
							"name": "search-all-categories-page",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/categories?page=1&size=5&sort=categoryName,desc",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"categories"
									],
									"query": [
										{
											"key": "page",
											"value": "1"
										},
										{
											"key": "size",
											"value": "5"
										},
										{
											"key": "sort",
											"value": "categoryName,desc"
										}
									]
								}
							},
							"response": []
						},
						{
							"name": "search-category-by-id",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/admin/categories/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"admin",
										"categories",
										"1"
									]
								}
							},
							"response": []
						}
					]
				}
			]
		},
		{
			"name": "User",
			"item": [
				{
					"name": "Cart",
					"item": [
						{
							"name": "get-cart",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/shopping-cart",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"shopping-cart"
									]
								}
							},
							"response": []
						},
						{
							"name": "add-to-cart",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "POST",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\"payload\":\r\n    {\r\n        \"productId\":4,\r\n        \"quantity\":15\r\n    }\r\n    }",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/shopping-cart",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"shopping-cart"
									]
								}
							},
							"response": []
						},
						{
							"name": "update-quantity-to-cart",
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "PUT",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\"payload\":\r\n    {\r\n        \"quantity\":15\r\n    }\r\n    }",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/shopping-cart/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"shopping-cart",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "checkout-order",
							"protocolProfileBehavior": {
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "POST",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\r\n    \"addressId\": 1\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/shopping-cart/checkout",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"shopping-cart",
										"checkout"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get-order",
							"protocolProfileBehavior": {
								"disableBodyPruning": true,
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\r\n    \"addressId\": 1\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/shopping-cart/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"shopping-cart",
										"1"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Account",
					"item": [
						{
							"name": "account-info",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/account",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"account"
									]
								}
							},
							"response": []
						},
						{
							"name": "update-account",
							"protocolProfileBehavior": {
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "PUT",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "formdata",
									"formdata": [
										{
											"key": "fullName",
											"value": "vu thanh tung",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "email",
											"value": "tung1721991@gmail.com",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "phone",
											"value": "0843628946",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "address",
											"value": "ha nội",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "file",
											"contentType": "multipart/form-data",
											"type": "file",
											"src": "/C:/Users/TungV/OneDrive/Máy tính/đăng fb thi/DSC01287.jpg"
										},
										{
											"key": "request",
											"value": "{\n\"fullName\":\"Vũ Thanh Tùng\",\n\"email\":\"tung1721@gmail.com\",\n\"phone\":\"0901112222\",\n\"address\":\"Hoàng Mai, Hà Nội\"\n}",
											"contentType": "application/json",
											"description": "{\n\"fullName\":\"Hồ Xuân Hùng\",\n\"email\":\"hung1234@gmail.com\",\n\"phone\":\"0993784672\",\n\"address\":\"Quận Tân Bình, TP Hồ Chí Minh\"\n}",
											"type": "text"
										}
									]
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/account",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"account"
									]
								}
							},
							"response": []
						},
						{
							"name": "change-password",
							"protocolProfileBehavior": {
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "PUT",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "formdata",
									"formdata": [
										{
											"key": "fullName",
											"value": "vu thanh tung",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "email",
											"value": "tung1721991@gmail.com",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "phone",
											"value": "0843628946",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "address",
											"value": "ha nội",
											"contentType": "",
											"type": "text",
											"disabled": true
										},
										{
											"key": "file",
											"contentType": "multipart/form-data",
											"type": "file",
											"src": "/C:/Users/TungV/OneDrive/Máy tính/đăng fb thi/DSC01287.jpg"
										},
										{
											"key": "request",
											"value": "{\n\"fullName\":\"Vũ Thanh Tùng\",\n\"email\":\"tung1721@gmail.com\",\n\"phone\":\"0901112222\",\n\"address\":\"Hoàng Mai, Hà Nội\"\n}",
											"contentType": "application/json",
											"description": "{\n\"fullName\":\"Hồ Xuân Hùng\",\n\"email\":\"hung1234@gmail.com\",\n\"phone\":\"0993784672\",\n\"address\":\"Quận Tân Bình, TP Hồ Chí Minh\"\n}",
											"type": "text"
										}
									]
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/account",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"account"
									]
								}
							},
							"response": []
						},
						{
							"name": "add-new-address",
							"protocolProfileBehavior": {
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "POST",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\r\n\"receiveName\":\"phuong thi\",\r\n\"fullAddress\": \"171 Xã đàn, Đống đa , Hà Nội\",\r\n\"phone\": \"0998378267\"\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/account/address",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"account",
										"address"
									]
								}
							},
							"response": []
						},
						{
							"name": "delete-address",
							"protocolProfileBehavior": {
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "DELETE",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/account/address/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"account",
										"address",
										"1"
									]
								}
							},
							"response": []
						},
						{
							"name": "get-address-by-Id",
							"protocolProfileBehavior": {
								"disableBodyPruning": true,
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/account/address/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"account",
										"address",
										"1"
									]
								}
							},
							"response": []
						},
						{
							"name": "get-all-address",
							"protocolProfileBehavior": {
								"disableBodyPruning": true,
								"disabledSystemHeaders": {}
							},
							"request": {
								"auth": {
									"type": "bearer",
									"bearer": [
										{
											"key": "token",
											"value": "{{token}}",
											"type": "string"
										}
									]
								},
								"method": "GET",
								"header": [
									{
										"key": "userId",
										"value": "{{userId}}"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8088/api.myservice.com/v1/user/account/address",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8088",
									"path": [
										"api.myservice.com",
										"v1",
										"user",
										"account",
										"address"
									]
								}
							},
							"response": []
						}
					]
				}
			]
		}
	]
}