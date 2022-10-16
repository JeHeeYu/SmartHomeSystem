
#include "esp_wifi.h"
#include "esp_log.h"

static const char *TAG = "WIFI";

static EventGroupHandle_t wifiEventGroupHandler;

static void WiFiInit()
{


    ESP_ERROR_CHECK(esp_netif_init());
    wifiEventGroupHandler = xEventGroupCreate();
    ESP_ERROR_CHECK(esp_event_loop_create_default());
}